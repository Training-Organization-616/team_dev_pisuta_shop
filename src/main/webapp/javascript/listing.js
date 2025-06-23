/**
 * 
 */

window.addEventListener('load',function(){
	const imageElm = document.querySelector("#preview");
	const inputElm = document.querySelector("#import_image");
	
	inputElm.addEventListener('change',event => {
		const file = event.target.files[0];
		const reader = new FileReader();
		reader.onload = () =>{
			imageElm.src = reader.result;
		};
		reader.readAsDataURL(event.target.files[0]);
	})
})