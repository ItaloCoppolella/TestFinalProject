document.addEventListener("DOMContentLoaded", function () {
  // Selezione del modulo e del pulsante Invia
  const contactForm = document.querySelector(".contact-form");
  const submitButton = contactForm.querySelector("button[type='submit']");

  // Aggiunta dell'evento di click al pulsante Invia
  submitButton.addEventListener("click", function (event) {
    // Verifica se tutti i campi sono stati compilati
    const firstName = document.getElementById("first-name").value;
    const lastName = document.getElementById("last-name").value;
    const email = document.getElementById("mail").value;
    const description = document.getElementById("description").value;

    if (firstName === "" || lastName === "" || email === "" || description === "") {
      alert("Devi compilare tutti i campi.");
      event.preventDefault(); // Impedisce l'invio del modulo
    } else {
      alert("Email inviata con successo.");
    }
  });
});
