/**
 * 
 */

window.addEventListener('load', function() {
	const dialog = document.querySelector('#deleteDialog');
	const deleteButton = document.querySelectorAll('.delete');
	const deleteId = document.querySelector('.delete_id');

	var item_list;
	fetch('http://localhost:8090/team_dev_pisuta_shop/ItemManageServlet?action=data')
		.then(response => response.json())
		.then(data => {
			item_list = data;
		})
		.catch(error => {
			console.log(error);
		});
		
		

	deleteButton.forEach(function(deleteButton) {
		deleteButton.addEventListener('click', () => {
			Object.keys(item_list).forEach((key) => {
				if (item_list[key]["id"] == deleteButton.value) {
					deleteId.value = deleteButton.value;
					const itemName = document.querySelector("#itemName");
					const itemPrice = document.querySelector("#itemPrice");
					itemName.innerHTML = "商品名:" + item_list[key]["name"];
					itemPrice.innerHTML = "価格:" + item_list[key]["price"] + "円";
				}
			});
			dialog.showModal();
		});
	})


});