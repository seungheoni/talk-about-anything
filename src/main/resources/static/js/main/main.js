window.onload = function() {
    // Friends 탭에 대한 이벤트 리스너 추가
    document.querySelector('.tablinks[data-tab="Friends"]').addEventListener('click', function(event) {
        openTab(event, 'Friends');
    });

    // Chats 탭에 대한 이벤트 리스너 추가
    document.querySelector('.tablinks[data-tab="Chats"]').addEventListener('click', function(event) {
        openTab(event, 'Chats');
    });

    // Get the modal
    var modal = document.getElementById("myModal");

    // Get the close button
    var closeButton = document.getElementsByClassName("close")[0];

    // When the user clicks on the close button, close the modal
    closeButton.onclick = function() {
        // Get the modal
        var modal = document.getElementById("myModal");

        // Remove the "open" class and add the "close" class
        modal.classList.remove("open");
        modal.classList.add("close");

        // After the animation is complete, hide the modal
        setTimeout(function() {
            modal.style.display = "none";
        }, 500);  // The duration of the animation
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
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

function addFriend() {
    // Get the modal
    var modal = document.getElementById("myModal");


    // Remove the "close" class and add the "open" class
    modal.classList.remove("close");
    modal.classList.add("open");

    // Open the modal
    modal.style.display = "block";
}

function addFriendSubmit(event) {
    event.preventDefault(); // 폼 제출 시 페이지 새로고침 방지

    // 입력된 친구 이름 가져오기
    var friendName = document.getElementById('friend-name').value;

    // AJAX를 사용하여 서버로 친구 이름 전송
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/add-friend', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // 성공적으로 친구가 추가되었을 때 실행할 코드
            console.log('친구 추가 성공');
            // 추가된 친구를 화면에 업데이트하는 로직을 작성하세요
        } else {
            // 친구 추가 실패 시 실행할 코드
            console.log('친구 추가 실패');
        }
    };

    var data = JSON.stringify({ friendName: friendName });
    xhr.send(data);
}