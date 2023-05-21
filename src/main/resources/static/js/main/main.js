function openTab(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
}
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
}
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";



    var addFriendButton = document.getElementById('add-friend-btn');
    var startChatButton = document.getElementById('start-chat-btn');
    if (tabName === 'Friends') {
        addFriendButton.style.display = 'inline-block';
        startChatButton.style.display = 'none';
    } else if (tabName === 'Chats') {
        addFriendButton.style.display = 'none';
        startChatButton.style.display = 'inline-block';
    } else {
        addFriendButton.style.display = 'none';
        startChatButton.style.display = 'none';
    }

}

function openChatRoom(element) {
    var roomId = element.getAttribute('data-room-id');
    window.location.href = '/chatRoom/' + roomId;
}
function addFriend() {

}
