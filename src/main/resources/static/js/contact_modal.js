document.addEventListener("DOMContentLoaded", function () {
  const successModal = document.getElementById('successfull_reservation');
  const reservationForm = document.querySelector('.reservation__form');
  const dateInput = reservationForm.querySelector('input[type="date"]');
  const timeInput = reservationForm.querySelector('input[type="time"]');
  const wrapper = document.getElementById('wrapper'); // Припускаючи, що існує елемент з класом 'wrapper'
  const currentPage = window.location.href;

  let currentDate = new Date().toISOString().split('T')[0];
  dateInput.setAttribute('min', currentDate);
  dateInput.value = currentDate;
  let currentTime = new Date().toLocaleTimeString('en-US', { hour12: false, hour: '2-digit', minute: '2-digit' });
  timeInput.value = currentTime;

  const phoneInput = reservationForm.querySelector('input[name="phone"]');
  
  phoneInput.addEventListener('input', function (e) {
    const regex = /[^\d]/g;
    phoneInput.value = phoneInput.value.replace(regex, '').substring(0, 10);
  });

  function closeModal() {
    successModal.style.display = "none";
    wrapper.classList.remove("blur");
  }

  function closeOnOutsideClick(event) {
    if (event.target !== successModal && !successModal.contains(event.target)) {
      closeModal();
    }
  }

  document.querySelectorAll(".close").forEach(function (closeBtn) {
    closeBtn.addEventListener("click", closeModal);
  });

  window.addEventListener("click", closeOnOutsideClick);

  reservationForm.addEventListener('submit', function (event) {
    event.preventDefault(); // Заборонити відправку форми для відлагодження

    let currentTime = new Date().toLocaleTimeString('en-US', { hour12: false });
    const firstName = reservationForm.querySelector('input[name="name"]').value;
    const lastName = reservationForm.querySelector('input[name="surname"]').value;
    const email = reservationForm.querySelector('input[name="email"]').value;
    const phone = reservationForm.querySelector('input[name="phone"]').value;
    const date = dateInput.value;
    const time = timeInput.value;

    const errorMessage = document.getElementById("error");

    if (!firstName || !lastName || !email || !phone || !date || !time || !currentPage.includes('contact')) {
      errorMessage.style.display = "block";
    } else {
      setTimeout(function () {
        successModal.style.display = "block";
        wrapper.classList.add("blur");
        
        function closeModalAndSubmitForm() {
          successModal.style.display = "none";
          wrapper.classList.remove("blur");
          if (reservationForm.checkValidity()) {
            reservationForm.submit(); // Спробуйте відправити форму
          }
        }

        // Додати обробники подій для закриття модального вікна та відправлення форми
        successModal.addEventListener("click", function (event) {
          if (event.target.classList.contains("close")) {
            closeModalAndSubmitForm();
          }
        });

        document.addEventListener("click", function (event) {
          if (!successModal.contains(event.target)) {
            closeModalAndSubmitForm();
          }
        });
      }, 300);
    }
  });
});
