document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    // Mostra la barra di caricamento
    document.getElementById('loadingBar').style.display = 'block';

    // Simula un ritardo per il caricamento
    setTimeout(function() {
        // Nasconde la barra di caricamento
        document.getElementById('loadingBar').style.display = 'none';

        if (username === 'Allenatore' && password === 'passwordAllenatore') {
            window.location.href = 'allenatore.html';
        } else if (username === 'Dirigente' && password === 'passwordDirigente') {
            window.location.href = 'dirigente.html';
        } else if (username === '' || password === '') {
            alert('Compila tutti i campi');
        } else {
            alert('Utente non trovato');
        }
    }, 2000); // 2000 millisecondi = 2 secondi
});
