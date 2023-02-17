const APPLICATION_CONTEXT_PATH = "";
const modal = document.querySelector(".modal");
const trigger = document.querySelector(".trigger");
const closeButton = document.querySelector(".close-button");
const avatarId = document.querySelector("#preview-avatar");
const avatarImageId = document.querySelector("#avatar-image-id");
const RATING_TIMEOUT = 3000;
const CODEWORD_TO_DELETE = "delete";
const QUEST_PAGE = APPLICATION_CONTEXT_PATH + "/quest";
const DELETE_QUEST_PAGE = APPLICATION_CONTEXT_PATH + "/delete-quest";
const DELETE_USER_PAGE = APPLICATION_CONTEXT_PATH + "/delete-user";
const AVATAR_IMAGE_CLASS = "profile-image";
const SHOW_MODAL_CLASS = "show-modal";
const RATING_VOTE_RESULT_ID = "#rating-vote-result";
const SO_FAST_MESSAGE = "Слишком быстро";
const BAD_PARAMETERS = "Что-то пошло не так";
const ASK_TO_DELETE_USER = "Вы точно хотите удалить пользователя: ";
const ASK_TO_DELETE_QUEST_1 = "Вы точно хотите удалить квест:";
const ASK_TO_DELETE_QUEST_2 = "Квест назад вернуть не получится!";
const ONLY_IMAGES_ALLOWED = "Разрешены только изображения";
const PROMPT_TO_DELETE_QUEST = "Для подтверждения удаления введите ниже";
let tooFast = false;

function getQSelector(el) {
    return document.querySelector(el);
}

function toggleModal() {
    modal.classList.toggle(SHOW_MODAL_CLASS);
}

function windowOnClick(event) {
    if (event.target === modal) {
        toggleModal();
    }
}

function getFileNameWithExtension(attr) {
    return attr.slice(attr.lastIndexOf("/") + 1);
}

if (trigger != null && closeButton) {
    trigger.addEventListener("click", toggleModal);
    closeButton.addEventListener("click", toggleModal);
    window.addEventListener("click", windowOnClick);
}

function chooseDefaultAvatar(el = null) {
    let imgSrcAttr = el.getAttribute("src");
    let img = document.createElement('img');
    img.setAttribute("class", AVATAR_IMAGE_CLASS)
    toggleModal() // optional...
    img.addEventListener("click", toggleModal)
    img.src = imgSrcAttr;
    removeAllChilds(avatarId)
    avatarId.appendChild(img);
    avatarImageId.setAttribute("value", getFileNameWithExtension(imgSrcAttr))
}

function removeAllChilds(elem) {
    if (elem.childElementCount > 0) {
        while (elem.lastElementChild) {
            elem.removeChild(elem.lastElementChild);
        }
    }
}

function previewUploadingImage(e) {
    e.onchange = evt => {
        const [file] = e.files
        if (file) {
            if (file['type'].split('/')[0] === 'image') {
                getQSelector("#image").value = file.name
                getQSelector("#preview-image").src = URL.createObjectURL(file)
            } else {
                alert(ONLY_IMAGES_ALLOWED)
            }
        }
    }
}

function removeElementById(id) {
    let el = document.getElementById(id);
    if (el != null) el.remove()
}

function showHideElement(idOrClass) {
    let element = idOrClass;
    if (typeof idOrClass === 'string')
        element = getQSelector(idOrClass);
    let display = element.style.display;
    if (display !== "block")
        element.style.display = "block";
    else
        element.style.display = "none";
}

function sendRequest(method, url, params = null) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest()
        xhr.open(method, url)
        xhr.responseType = 'text'
        xhr.setRequestHeader('Content-Type', 'application/json')
        xhr.onload = () => {
            if (xhr.status >= 300) {
                window.location = "/"
            }
            resolve(xhr.response);
        }
        xhr.onerror = () => {
            reject(xhr.response)
        }
        xhr.send(JSON.stringify(params))
    });
}


function deleteQuest(id, title) {
    let askToDelete = window.confirm(ASK_TO_DELETE_QUEST_1 + title + " ?\n" +
        ASK_TO_DELETE_QUEST_2)
    if (askToDelete) {
        let deletingPrompt = window.prompt(PROMPT_TO_DELETE_QUEST + ": " + CODEWORD_TO_DELETE)
        if (deletingPrompt === CODEWORD_TO_DELETE) {
            sendRequest('DELETE', DELETE_QUEST_PAGE, {id: [id]})
                .then(removeFromTable(id))
                .catch(() => alert(BAD_PARAMETERS))
        }
    }
}

function deleteUser(id, login) {
    let askToDelete = window.confirm(ASK_TO_DELETE_USER + login + " ?");
    if (askToDelete) {
        sendRequest('DELETE', DELETE_USER_PAGE, {id: [id]})
            .then(removeFromTable(id))
            .catch(() => alert(BAD_PARAMETERS))
    }
}

function removeFromTable(id) {
    return () => {
        removeElementById(id);
    };
}

function questRating(params) {
    if (!tooFast) {
        tooFast = true
        sendRequest('POST', QUEST_PAGE, params)
            .then(response => {
                let ratingId = getQSelector("#rating");
                ratingId.textContent = response
                showHideElement(RATING_VOTE_RESULT_ID)
                setTimeout(() => {
                    showHideElement(RATING_VOTE_RESULT_ID)
                    tooFast = false
                }, RATING_TIMEOUT)
            })
            .catch(() => alert(BAD_PARAMETERS))
    } else {
        alert(SO_FAST_MESSAGE)
    }
}