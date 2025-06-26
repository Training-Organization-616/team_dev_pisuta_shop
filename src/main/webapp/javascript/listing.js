/**
 * 
 */
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

	btnElm.addEventListener('click', async (e) => {
		const opts = {
			types: [
				{
					description: '画像',
					accept: {
						'image/png': ['.png'],
					},
				}
			],
			excludeAcceptAllOption: true,
			multiple: false,
		};
		try {
			const fh_list = await window.showOpenFilePicker(opts);
			const fh = fh_list[0];
			const file = await fh.getFile();
			const dataTransfer = new DataTransfer();
			dataTransfer.items.add(file);
			inputElm.files = dataTransfer.files;

			const reader = new FileReader();
			reader.onload = () => {
				imageElm.src = reader.result;
			};
			reader.readAsDataURL(file);
		} catch (error) {

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
	imageElm.addEventListener('dragenter', function(e) {
		e.stopPropagation();
		e.preventDefault();
		imageElm.classList.add('hov_m');
	}, false);

	imageElm.addEventListener('dragover', function(e) {
		e.stopPropagation();
		e.preventDefault();
	}, false);

	imageElm.addEventListener('dragleave', e => {
		e.stopPropagation();
		e.preventDefault();
		imageElm.classList.remove('hov_m');
	});

	imageElm.addEventListener('drop', drop, false);



})