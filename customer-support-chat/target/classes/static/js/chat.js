let stompClient=null;let roomId=null;let nameInput=null;let role=null;
const el=(id)=>document.getElementById(id);
const connectBtn=el("connectBtn");const disconnectBtn=el("disconnectBtn");
function connect(){
  roomId=el("roomId").value.trim();nameInput=el("name").value.trim();role=el("role").value;
  if(!roomId||!nameInput)return;
  const socket=new SockJS("/ws");stompClient=Stomp.over(socket);stompClient.debug=null;
  stompClient.connect({},()=>{
    connectBtn.disabled=true;disconnectBtn.disabled=false;
    document.querySelector("#chat").style.display="block";
    stompClient.subscribe("/topic/room."+roomId,(msg)=>{
      const body=JSON.parse(msg.body);renderMessage(body);
    });
    stompClient.send("/app/room/"+roomId+"/join",{},JSON.stringify({roomId:roomId,user:nameInput,role:role}));
  });
}
function disconnect(){
  if(stompClient){stompClient.disconnect(()=>{});}stompClient=null;
  connectBtn.disabled=false;disconnectBtn.disabled=true;document.querySelector("#chat").style.display="none";
  el("messages").innerHTML="";
}
function send(){
  if(!stompClient)return;const content=el("message").value.trim();if(!content)return;
  stompClient.send("/app/room/"+roomId+"/send",{},JSON.stringify({roomId:roomId,sender:nameInput,content:content,role:role}));
  el("message").value="";
}
function renderMessage(m){
  const li=document.createElement("li");
  if(m.sender==="system"){li.className="system";li.textContent=m.content;el("messages").appendChild(li);scroll();return;}
  li.className=m.sender===nameInput?"me":"other";
  li.innerHTML="<strong>"+m.sender+(m.role?(" ("+m.role+")"):"")+"</strong><br/>"+escapeHtml(m.content);
  el("messages").appendChild(li);scroll();
}
function escapeHtml(s){return s.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;");}
el("sendBtn").addEventListener("click",send);
el("message").addEventListener("keydown",(e)=>{if(e.key==="Enter")send();});
connectBtn.addEventListener("click",connect);disconnectBtn.addEventListener("click",disconnect);
