document.addEventListener("DOMContentLoaded", function() {
    const passwordField = document.querySelector('input[name="password"]');
    const confirmPasswordField = document.querySelector('input[name="confirm_password"]');
    const differentPasswordsError = document.getElementById('different_passwords');
    const shortPasswordsError = document.getElementById('short_passwords');
    const weakPasswordError = document.getElementById('weak_password');
    const fillError = document.getElementById('fill_error');
    const signUpForm = document.querySelector('.register_form');
    const phoneNumberField = document.querySelector('input[name="phone"]');

    differentPasswordsError.style.display = 'none';
    shortPasswordsError.style.display = 'none';
    weakPasswordError.style.display = 'none';
    fillError.style.display = 'none';

    phoneNumberField.addEventListener('input', function() {
        // Обмежуємо введення лише цифр та до 10 символів
        const regex = /[^\d]/g;
        phoneNumberField.value = phoneNumberField.value.replace(regex, '').substring(0, 10);
    });

    signUpForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Зупиняємо стандартну відправку форми

        const password = passwordField.value;
        const confirmPassword = confirmPasswordField.value;
        const surname = document.querySelector('input[name="surname"]').value;
        const name = document.querySelector('input[name="name"]').value;
        const email = document.querySelector('input[name="email"]').value;
        const phoneNumber = phoneNumberField.value;

        if (!surname || !name || !email || !phoneNumber || !password || !confirmPassword) {
            fillError.style.display = 'block';
        } else {
            fillError.style.display = 'none';

            if (password !== confirmPassword) {
                differentPasswordsError.style.display = 'block';
            } else {
                differentPasswordsError.style.display = 'none';
            }

            if (password.length < 8) {
                shortPasswordsError.style.display = 'block';
            } else {
                shortPasswordsError.style.display = 'none';
            }

            const containsLetter = /[a-zA-Z]/.test(password);

            if (!containsLetter) {
                weakPasswordError.style.display = 'block';
            } else {
                weakPasswordError.style.display = 'none';
            }

            if (differentPasswordsError.style.display === 'none' &&
                shortPasswordsError.style.display === 'none' &&
                weakPasswordError.style.display === 'none') {
                this.submit();
            }
        }
    });
});
