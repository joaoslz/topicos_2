/* Ativa o componente tooltip do bootstrap */
$(function () {
   $('[data-toggle="tooltip"]').tooltip();
})



// consultar
// http://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html#script-inlining-javascript-and-dart

$(document).ready( function() {
	
 $('.excluir-livro-js').click(function() {
	 
	/*<![CDATA[*/
     var path = /*[[@{/}]]*/ 'remove';
    /*]]>*/
	
    var id = $(this).data('id');

	console.log("id :");
	console.log(id);
	
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
	        	   console.log('o livro foi removido com sucesso');
	           })
	    }
	}); // bootbox.confirm()  
	
 });

 $('#excluir-livros').click(function() {
		/*<![CDATA[*/
	        var path = /*[[@{/}]]*/ 'removeLista';
	    /*]]>*/
	        
	        var id = $(this).attr('id');
	    	
	    	bootbox.confirm({
	    	    title: "Excluir Livro?",
	    	    message: "Confirma a exclusão de todos os livros selecionados",
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
	    	           $.ajax({
	    	        	   type: 'POST',
	    	        	   url: path,
	    	        	   data: JSON.stringify(listaLivroId),
	    	        	   contentType: 'application/json',
	    	        	   success: function(resposta) { 
	    	        		               console.log(resposta);
	    	        		               location.reload();
	    	        	   },
	    	               error: function(resposta) { 
	    	            	   			   console.log(resposta);
	    	            	   			   location.reload();
    	                   }
	    	         });
	    	    }
	    	}); // bootbox.confirm()
	});

    // código da lista de livros selecionadas no checkbox
	var listaLivroId = [];
	
	$('.checkboxlivro-js').click( function() {
		
		var id = $(this).attr('id');
		
		if ( this.checked ) {
			listaLivroId.push(id);
		
		} else {
			listaLivroId.splice(listaLivroId.indexOf(id), 1);
		}
		console.log(listaLivroId );
	});

    // código para selecionar todos os livros da página
	$('#todoscheckbox-js').click(function () {
		console.log('teste checkbox');
		listaLivroId = [];
		if (this.checked) {
			$('.checkboxlivro-js').prop('checked', true);
			$('.checkboxlivro-js').each(function() {
				 listaLivroId.push( $(this).attr('id') );
			});
	
		} else {
			$('.checkboxlivro-js').prop('checked', false);
	    }
		console.log(listaLivroId);
	}); 	
	
}); // função ready()

