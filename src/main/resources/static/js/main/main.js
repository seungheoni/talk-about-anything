window.onload = function() {
    // Friends 탭에 대한 이벤트 리스너 추가
    document.querySelector('.tablinks[data-tab="Friends"]').addEventListener('click', function(event) {
        openTab(event, 'Friends');
    });

    // Chats 탭에 대한 이벤트 리스너 추가
    document.querySelector('.tablinks[data-tab="Chats"]').addEventListener('click', function(event) {
        openTab(event, 'Chats');
    });

    var modal = document.getElementById("myModal");

    var closeButton = document.getElementsByClassName("close")[0];

    closeButton.onclick = function() {
        var modal = document.getElementById("myModal");

        modal.classList.remove("open");
        modal.classList.add("close");

        setTimeout(function() {
            modal.style.display = "none";
        }, 500);
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    let addFriendSubmit = document.getElementById('add-friend-submit');
    let csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    let csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    addFriendSubmit.addEventListener('click', function(event){
        event.preventDefault();
        let friendName = document.getElementById('friend-name').value;
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "/api/v1/add-friend", true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.setRequestHeader(csrfHeader, csrfToken);

        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 204) {
                    // 성공적으로 친구가 추가되었을 때 실행할 코드
                    alert('친구 추가에 성공 했습니다');
                    updateFriendList();
                } else {
                    // 친구 추가 실패 시 실행할 코드
                    alert('친구 추가 실패');
                }
            }
        };

        xhr.send(JSON.stringify({friendName: friendName}));
    });



    // 새로운 채팅방 생성하기
    document.querySelector('#start-chat-btn').addEventListener('click', startChat);

    var chatCloseButton = document.getElementById("chatModal").getElementsByClassName("close")[0];
    chatCloseButton.onclick = function() {
        var modal = document.getElementById("chatModal");
        modal.classList.remove("open");
        modal.classList.add("close");
        setTimeout(function() {
            modal.style.display = "none";
        }, 500);
    }
};

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

function addFriendOpenModal() {

    var modal = document.getElementById("myModal");

    modal.classList.remove("close");
    modal.classList.add("open");

    modal.style.display = "block";
}

function updateFriendList() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/api/v1/friends', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            var friends = JSON.parse(xhr.responseText);
            var friendListElement = document.getElementById('Friends').getElementsByTagName('ul')[0];

            // 기존 친구 목록을 비우기
            while (friendListElement.firstChild) {
                friendListElement.removeChild(friendListElement.firstChild);
            }

            // 새 친구 목록을 추가하기
            for (var i = 0; i < friends.length; i++) {
                var li = document.createElement('li');
                li.textContent = friends[i].friendName;
                friendListElement.appendChild(li);
            }

        }
    };

    xhr.send();
}

function startChat() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/api/v1/friends', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            var friends = JSON.parse(xhr.responseText);
            var friendListElement = document.getElementById('start-chat-form');

            // 기존 친구 목록을 비우기
            while (friendListElement.firstChild) {
                friendListElement.removeChild(friendListElement.firstChild);
            }

            // 새 친구 목록을 추가하기
            for (var i = 0; i < friends.length; i++) {
                var div = document.createElement('div');

                var input = document.createElement('input');
                input.type = "checkbox";
                input.id = friends[i].friendName;
                input.value = friends[i].friendName;

                var label = document.createElement('label');
                label.htmlFor = friends[i].friendName;
                label.textContent = friends[i].friendName;

                div.appendChild(input);
                div.appendChild(label);
                friendListElement.appendChild(div);
            }

            var modal = document.getElementById("chatModal");

            modal.classList.remove("close");
            modal.classList.add("open");

            modal.style.display = "block";
        }
    };

    xhr.send();
}