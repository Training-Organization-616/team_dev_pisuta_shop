/**
 * 
 */
function dragenter(e) {
	e.stopPropagation();
	e.preventDefault();
}
function dragover(e) {
	e.stopPropagation();
	e.preventDefault();
}


window.addEventListener('load', function() {
	const imageElm = document.querySelector("#preview");
	const inputElm = document.querySelector("#import_image");
	const btnElm = document.querySelector("#select_file");
	const imgElm = document.querySelector("#preview");

	inputElm.addEventListener('change', event => {
		const file = event.target.files[0];
		const reader = new FileReader();

		if (event.target.files.length > 0) {
			reader.onload = () => {
				imageElm.src = reader.result;
			};
			reader.readAsDataURL(event.target.files[0]);
		}
	})

	btnElm.addEventListener('click', (e) => {
		if (inputElm) {
			inputElm.click();
		}
	}, false)

	function drop(e) {
		e.stopPropagation();
		e.preventDefault();

		const dt = e.dataTransfer;
		let types = e.dataTransfer.types;
		let isFiles = types.filter(_ => _ == "Files").length > 0;
		if (isFiles) {
			const file = dt.files[0];
			const d = new DataTransfer();
			d.items.add(file);
			inputElm.files = d.files;
			const reader = new FileReader();
			reader.onload = () => {
				imageElm.src = reader.result;
			};
			reader.readAsDataURL(file);
		}
	}

	imageElm.addEventListener('dragenter', dragenter, false);
	imageElm.addEventListener('dragover', dragover, false);
	imageElm.addEventListener('drop', drop, false);



})