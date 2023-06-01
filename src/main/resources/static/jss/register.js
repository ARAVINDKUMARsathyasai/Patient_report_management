document.addEventListener('DOMContentLoaded', function() {
	const nameInput = document.getElementById('loginName');
    const emailInput = document.getElementById('loginEmail');
    const passwordInput = document.getElementById('loginPassword');
    const submitButton = document.getElementById('submit');

    submitButton.addEventListener('mouseover', function() {
        let email = emailInput.value;
        let password = passwordInput.value;
        let name = nameInput.value;
        
        let validate = /^([a-zA-Z0-9\._]+)@([a-zA-Z0-9]+).([a-z]+)?/.test(email) && /[a-zA-Z0-9]{8}/.test(password)&& /[a-zA-Z]/.test(name);

        if (!validate) {
            submitButton.classList.toggle('move');
        }
    });
});
