function validate(form) {
		var e = form.elements;
		if (e['confirm-password'].value != document.getElementById('password').value) {
			alert('Your passwords do not match. Please type more carefully.');
			return false;
		}
		
		return true;
	}


/*function setRelativelySize(img) {
	if(img.height > 300) {
		img.style.height = '300px';
		img.style.width = 'auto';
	} else if (img.width > 300) {
        img.style.height = 'auto';
        img.style.width = '300px';		
	}
}*/