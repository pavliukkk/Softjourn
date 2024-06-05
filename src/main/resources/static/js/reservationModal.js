document.addEventListener("DOMContentLoaded", function() {
    const reservationForm = document.getElementById("reservationForm");
    const successModal = document.getElementById("successfull_reservation");
    const wrapper = document.getElementById("wrapper");

    reservationForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Заборона типової події відправки форми

        const dateInput = document.querySelector('input[type="date"]');
        const timeInput = document.querySelector('input[type="time"]');

        if (!dateInput.value || !timeInput.value) {
            // Показати відповідне повідомлення про незаповнені поля
            if (!dateInput.value && !timeInput.value) {
                document.getElementById("select_both").style.display = "block";
            } else if (!dateInput.value) {
                document.getElementById("select_date").style.display = "block";
            } else {
                document.getElementById("select_time").style.display = "block";
            }
        } else {
            // Показати модальне вікно після затримки (наприклад, 2 секунди)
            setTimeout(function() {
                successModal.style.display = "block";
                wrapper.classList.add("blur");
            
                function closeModalAndSubmitForm() {
                    successModal.style.display = "none";
                    wrapper.classList.remove("blur");
                    if (reservationForm.checkValidity()) {
                        reservationForm.submit(); // Надсилаємо форму після закриття модального вікна
                    }
                }
            
                // Додаємо обробник кліку для модального вікна
                successModal.addEventListener("click", function(event) {
                    if (event.target.classList.contains("close")) {
                        closeModalAndSubmitForm();
                    }
                });
            
                // Додаємо обробник кліку для всього документу
                document.addEventListener("click", function(event) {
                    if (!successModal.contains(event.target)) {
                        closeModalAndSubmitForm();
                    }
                });
            }, 300);
        }
    });
});
