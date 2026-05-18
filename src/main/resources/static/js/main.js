
// CONFIRMACION DE ELIMINACION
// Agrega confirmacion a todos los botones de eliminar
document.addEventListener('DOMContentLoaded', function () {

    // Selecciona todos los formularios de eliminacion
    const deleteForms = document.querySelectorAll('form[action*="delete"]');

    deleteForms.forEach(function (form) {
        form.addEventListener('submit', function (e) {
            // Pregunta confirmacion antes de eliminar
            const confirmed = confirm('¿Estás seguro de que deseas eliminar este registro?');
            if (!confirmed) {
                // Cancela el envio del formulario si el usuario dice No
                e.preventDefault();
            }
        });
    });

    // ==========================================
    // OCULTAR ALERTAS AUTOMATICAMENTE
    // ==========================================
    // Las alertas de exito y error desaparecen despues de 4 segundos
    const alerts = document.querySelectorAll('.alert');

    alerts.forEach(function (alert) {
        setTimeout(function () {
            // Agrega clase para transicion suave
            alert.style.transition = 'opacity 0.5s';
            alert.style.opacity = '0';
            // Elimina el elemento despues de la transicion
            setTimeout(function () {
                alert.remove();
            }, 500);
        }, 4000);
    });
});