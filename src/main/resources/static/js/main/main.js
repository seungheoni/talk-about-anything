window.onload = function() {
    // Friends 탭에 대한 이벤트 리스너 추가
    document.querySelector('.tablinks[data-tab="Friends"]').addEventListener('click', function(event) {
        openTab(event, 'Friends');
    });

    // Chats 탭에 대한 이벤트 리스너 추가
    document.querySelector('.tablinks[data-tab="Chats"]').addEventListener('click', function(event) {
        openTab(event, 'Chats');
    });

    var modal = document.getElementById("addFriendModal");

    var closeButton = document.getElementsByClassName("close")[0];

    closeButton.onclick = function() {
        var modal = document.getElementById("addFriendModal");

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

        sendAjaxRequest("POST", "/api/v1/friends",
            {"Content-Type": "application/json;charset=UTF-8", [csrfHeader]: csrfToken},
            {friendName: friendName},
            function(xhr) {
                if (xhr.status === 204) {
                    alert('친구 추가에 성공 했습니다');
                    updateFriendList();
                } else {
                    alert('친구 추가 실패');
                }
            }
        );
    });

    // 새로운 채팅방 생성하기 모달창 뜨우기
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

    // 새로운 채팅방 생성하기
    document.querySelector('#start-chat-submit').addEventListener('click', function(event){
        event.preventDefault();
        let selectedFriends = Array.from(document.querySelectorAll('#start-chat-form input:checked'), input => input.value);

        sendAjaxRequest("POST", "/api/v1/chatrooms",
            {"Content-Type": "application/json;charset=UTF-8", [csrfHeader]: csrfToken},
            selectedFriends,
            function(xhr) {
                if (xhr.status === 204) {
                    alert('채팅방이 성공적으로 생성되었습니다.');
                    var chatModal = document.getElementById("chatModal");
                    chatModal.classList.remove("open");
                    chatModal.classList.add("close");
                    setTimeout(function() {
                        chatModal.style.display = "none";
                    }, 500);

                    //채팅방 목록 업데이트
                    updateChatRoomList();
                } else {
                    alert('채팅방 생성 실패');
                }
            }
        );
    });

    document.querySelector('#edit-friend-submit').addEventListener('click', function(event){
        event.preventDefault();

        let chatUserId = document.getElementById('new-friend-id').value;
        let newFriendName = document.getElementById('new-friend-name').value;
        let csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        let csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        sendAjaxRequest("PUT", "/api/v1/profiles",
            {"Content-Type": "application/json;charset=UTF-8", [csrfHeader]: csrfToken},
            {chatUserId: chatUserId, nameToChange: newFriendName},
            function(xhr) {
                if (xhr.status === 204) {
                    alert('프로필 이름이 변경되었습니다.');
                    updateFriendList();
                } else {
                    alert('프로필 이름 변경 실패');
                }
            }
        );
    });

    // 친구 목록 업데이트
    updateFriendList();

    // 채팅방 목록 업데이트
    updateChatRoomList();
};

// 새로운 친구 이름 변경 모달을 띄우는 함수
function openEditFriendModal(friendName,chatUserId) {
    var modal = document.getElementById("editFriendModal");
    var closeButton = modal.getElementsByClassName("close")[0];

    // 클릭 이벤트 리스너 설정
    closeButton.onclick = function() {
        modal.classList.remove("open");
        modal.classList.add("close");

        setTimeout(function() {
            modal.style.display = "none";
        }, 500);
    };

    // 현재 친구 이름을 입력 필드에 표시
    document.getElementById('new-friend-name').value = friendName;
    document.getElementById('new-friend-id').value = chatUserId;

    // 모달 열기
    modal.classList.remove("close");
    modal.classList.add("open");
    modal.style.display = "block";
}

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

    // 새로운 친구 목록 업데이트
    updateFriendList();
}

function openChatRoom(element) {
    var roomId = element.getAttribute('data-room-id');
    window.location.href = '/chatRoom/' + roomId;
}

function addFriendOpenModal() {

    var modal = document.getElementById("addFriendModal");

    modal.classList.remove("close");
    modal.classList.add("open");

    modal.style.display = "block";
}

function updateFriendList() {
    sendAjaxRequest('GET', '/api/v1/friends',
        {'Content-Type': 'application/json'},
        null,
        function(xhr) {
            if (xhr.status === 200) {
                var friends = JSON.parse(xhr.responseText);
                var friendListElement = document.getElementById('Friends').getElementsByTagName('ul')[0];
                while (friendListElement.firstChild) {
                    friendListElement.removeChild(friendListElement.firstChild);
                }
                for (var i = 0; i < friends.length; i++) {
                    var li = document.createElement('li');
                    var input = document.createElement('input');
                    input.type = 'hidden';
                    input.value = friends[i].chatUserId;
                    li.textContent = friends[i].friendName;
                    li.addEventListener('click', function(event) {
                        openEditFriendModal(event.target.textContent,event.target.lastElementChild.value);
                    });

                    li.appendChild(input);

                    friendListElement.appendChild(li);
                }
            }
        }
    );
}

function startChat() {
    sendAjaxRequest('GET', '/api/v1/friends',
        {'Content-Type': 'application/json'},
        null,
        function(xhr) {
            if (xhr.status === 200) {
                var friends = JSON.parse(xhr.responseText);
                var friendListElement = document.getElementById('start-chat-form');
                while (friendListElement.firstChild) {
                    friendListElement.removeChild(friendListElement.firstChild);
                }
                for (var i = 0; i < friends.length; i++) {
                    var div = document.createElement('div');

                    var input = document.createElement('input');
                    input.type = "checkbox";
                    input.id = friends[i].friendName;
                    input.value = friends[i].chatUserId;

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
        }
    );
}

function sendAjaxRequest(method, url, headers, body, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open(method, url, true);

    // 헤더 설정
    for (let key in headers) {
        xhr.setRequestHeader(key, headers[key]);
    }

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            callback(xhr);
        }
    };

    xhr.send(JSON.stringify(body));
}

function updateChatRoomList() {
    sendAjaxRequest('GET', '/api/v1/chatrooms',
        {'Content-Type': 'application/json'},
        null,
        function(xhr) {
            if (xhr.status === 200) {
                var chatrooms = JSON.parse(xhr.responseText);
                var chatroomsListElement = document.getElementById('Chats').getElementsByTagName('ul')[0];
                while (chatroomsListElement.firstChild) {
                    chatroomsListElement.removeChild(chatroomsListElement.firstChild);
                }
                for (var i = 0; i < chatrooms.length; i++) {
                    var li = document.createElement('li');
                    li.textContent = chatrooms[i].name;
                    li.setAttribute('data-room-id', chatrooms[i].id);
                    li.addEventListener('click', function(event) {
                        openChatRoom(event.target);
                    });

                    chatroomsListElement.appendChild(li);
                }
            }
        }
    );
}