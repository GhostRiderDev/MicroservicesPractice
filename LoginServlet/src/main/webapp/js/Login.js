function validate() {
    const username = document.loginForm.username.value;
    const password = document.loginForm.password.value;
    if(!username || username === "") {
        alert("Username should be not empty");
        return false;
    }
    if (!password || password === "") {
        alert("Password should be not empty");
        return false;
    }
    return true;
}

function validateRegister() {
    const name = document.registerForm.name.value;
    const username = document.registerForm.username.value;
    const password = document.registerForm.password.value;
    const repassword = document.registerForm.repassword.value;

    return (!username || !name || !password || !repassword ||
     name === "" || username === "" || password === "" || repassword === "")
}