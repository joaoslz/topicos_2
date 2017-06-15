/* Ativa o componente tooltip do bootstrap */
$(function () {
  $('[data-toggle="tooltip"]').tooltip();
})



// consultar 12.2 Script inlining (JavaScript and Dart)
// http://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html#script-inlining-javascript-and-dart

$(document).ready( function() {
	/*<![CDATA[*/
     var path = /*[[@{/}]]*/ 'remove';
    /*]]>*/
	
	var id = $(this).attr('id');
	
	bootbox.confirm({
	    title: "Excluir Livro?",
	    message: "Confirma a exclusão do livro",
	    buttons: {
	        cancel: {
	            label: '<i class="fa fa-times"></i> Cancelar'
	        },
	        confirm: {
	            label: '<i class="fa fa-check"></i> Confirmar'
	        }
	    },
	    callback: function (confirmado) {
	    	if (confirmado)
	           $.post(path, {'id':id}, function(resposta) {
	        	   location.reload();
	           })
	    }
	});
});

