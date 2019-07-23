
// submit click で form action 操作
$(function(){

	$('input[type="submit"]').click(function(){
		$(this).parents('form').attr('action', $(this).data('action'));
		$(this).parents('form').submit();
	})

});

// checkbox に 対応し 削除ボタン 操作
$(function(){

	$('.delete_btn').prop('disabled', true);
	$('.delete_btn').css('opacity', 0.1);
	$('.delete_btn').css('cursor', 'default');

	$('input[type="checkbox"]').click(function() {
		var check_count = $('input[type="checkbox"]:checked').length;
		if(check_count == 0){
			$('.delete_btn').prop('disabled', true);
			$('.delete_btn').animate({opacity:0.1},{duration: 200});
			$('.delete_btn').css('cursor', 'default');
		}else if(check_count > 0){
			$('.delete_btn').prop('disabled', false);
			$('.delete_btn').animate({opacity:1},{duration: 200});
			$('.delete_btn').css('cursor', 'pointer');
		}
	});

});