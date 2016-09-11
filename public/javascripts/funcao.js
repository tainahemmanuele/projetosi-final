/**
 * Created by Tainah Emmanuele Silva on 10/09/2016.
 */
$(document).ready(function () {
    $('#notification_counter')
        .css({ opacity: 0 })
        .text('!')              // Aqui você adiciona a quantidade de novas notificações(o que vem do server, por exemplo.).
        .css({ top: '-10px' })
        .animate({ top: '-2px', opacity: 1 }, 500);

    $('#notification_button').click(function () {
        // Ativa(Mostrando ou escondendo) a janela de notificações.
        $('#notifications').fadeToggle('fast', 'linear', function () {
            if ($('#notifications').is(':hidden')) {
                $('#notification_button').css('background-color', '#2E467C');
            }
            else $('#notification_button').css('background-color', '#FFF');        // Muda a cor do background.
        });

        $('#notification_counter').fadeOut('slow');                 // Apaga o numero de novas notificações.

        return false;
    });

    // Esconde as notificações quando clica em qualquer outra parte da página.
    $(document).click(function () {
        $('#notifications').hide();

        // Verifica se o número de novas notificações está escondido. Apenas pra mudar a cor do button.
        if ($('#notification_counter').is(':hidden')) {
            $('#notification_button').css('background-color', '#2E467C');
        }
    });

    $('#notifications').click(function () {
        return false;       // Não faz nada quando clica no container. Pode ser adicionado suas funções de click em um notificação.
    });
});