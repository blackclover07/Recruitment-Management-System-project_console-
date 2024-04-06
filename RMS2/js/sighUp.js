function passwordValidation() {
    let password = document.querySelector("#validationCustom05").value;
    let passDiv = document.querySelector(".passwordError");
    let capital = /[A-Z]/; // Corrected regular expression to check for uppercase letters
    let numeric = /[0-9]/; // Regular expression to check for numeric characters

    if (password.length < 8) {
        Swal.fire({
            title: 'Error!',
            text: 'Password must have 8 characters',
            icon: 'error',
            confirmButtonText: 'Ok'
        });
    } else if (!capital.test(password)) {
        Swal.fire({
            title: 'Error!',
            text: 'Password must contain at least one uppercase letter',
            icon: 'error',
            confirmButtonText: 'Ok'
        });
    } else if (!numeric.test(password)) {
        Swal.fire({
            title: 'Error!',
            text: 'Password must contain at least one numeric digit',
            icon: 'error',
            confirmButtonText: 'Ok'
        });
    } else if (!hasSpecialCharacter(password)) {
        Swal.fire({
            title: 'Error!',
            text: 'Password must contain at least one special character',
            icon: 'error',
            confirmButtonText: 'Ok'
        });
    } else {
        passDiv.textContent = ""; // Clear error message if password is valid
        return true; // Password is valid
    }

    return false; // Password is invalid
}

function hasSpecialCharacter(password) {
    const specialCharacters = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
    return specialCharacters.test(password);
}
