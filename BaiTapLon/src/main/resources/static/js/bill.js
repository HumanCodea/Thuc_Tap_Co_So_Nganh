
let screeningId = null;
let quanityTicket = null;
let selectedSeats = [];
let selectedPromotion = null;
let navItem = document.querySelector(".nav-item")
let dropdownMenyu = document.querySelector('.dropdown-menu')

navItem.addEventListener('click', function(){
    dropdownMenyu.classList.toggle('hide')
})

let selectedRow = null;

function selectShowtime(row) {
    if (selectedRow) {
        selectedRow.classList.remove('selected');
    }
    selectedRow = row;
    row.classList.add('selected');

    screeningId = row.querySelector('.sreeningId').getAttribute('data-id');
}

// seat section
document.addEventListener("DOMContentLoaded", () => {
    const seatContainer = document.getElementById("seat-selection");
    const rows = ["A", "B", "C", "D", "E", "F"];
    const cols = Array.from({ length: 10 }, (_, i) => i + 1);

    rows.forEach(row => {
        cols.forEach(col => {
            const seat = document.createElement("div");
            seat.classList.add("seat");
            seat.innerText = `${row}${col}`;

            seat.onclick = () => {
                seat.classList.toggle("selected");
                const seatName = seat.innerText
                if(seat.classList.contains("selected")){
                    selectedSeats.push(seatName)
                } else{
                    selectedSeats = selectedSeats.filter(name => name !== seatName)
                }
                quanityTicket = selectedSeats.length
            };
            seatContainer.appendChild(seat);
        });
    });
});

// Promotion selection function
function selectPromotion(element) {
    if (element.classList.contains('selected')) {
        element.classList.remove('selected');
        selectedPromotion = null; // Đặt lại giá trị của khuyến mãi đã chọn
    } else {
        // Bỏ class 'selected' khỏi tất cả các phần tử khác
        const promotions = document.querySelectorAll('.promotion');
        promotions.forEach(promo => promo.classList.remove('selected'));
        
        // Đánh dấu mục hiện tại là 'selected'
        element.classList.add('selected');
        selectedPromotion = element.querySelector('.promotionId').getAttribute('data-id'); // Lưu lại ID khuyến mãi 
    }
}

// ham gui api
document.getElementById("checkout-btn").onclick = () => {

    const selectedFood = getSelectedFood()

    // Kiểm tra nếu không có ghế được chọn
    if (selectedSeats.length === 0) {
        alert("Vui lòng chọn ít nhất một ghế!");
        return;
    }

    // kiem tra suat chieu
    if (screeningId === null) {
        alert("Vui lòng chọn ít nhất một suất chiếu!");
        return;
    }

    // console.log(screeningId)
    // console.log(selectedFood)
    // console.log(quanityTicket)
    // console.log(selectedSeats)
    // console.log(selectedPromotion)

    // Tạo đối tượng dữ liệu để gửi lên server
    const orderData = {
        screeningId: screeningId,       // ID suất chiếu
        selectedSeats: selectedSeats,   // Danh sách ghế đã chọn
        promotionId: selectedPromotion, // Mã khuyến mãi đã chọn (nếu có)
        foodItems: selectedFood,        // Danh sách món ăn đã chọn
        quanityTicket: quanityTicket   // Tổng số vé
    };

    // Gửi dữ liệu lên server qua API
    fetch('/saveBill', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(orderData)  // Chuyển đối tượng thành JSON
    })

};

// selectFood
function getSelectedFood() {
    let selectedFood = [];

    // Lấy tất cả các hàng (tr) trong bảng Food
    const rows = document.querySelectorAll("tr");

    rows.forEach(row => {
        // Lấy ID món ăn
        const foodId = row.querySelector("#foodId");
        
        // Nếu không có ID món ăn, bỏ qua row này
        if (!foodId) return;

        const quantityInput = row.querySelector("input.sl");
        const quantity = parseInt(quantityInput.value, 10);

        // Chỉ thu thập thông tin nếu số lượng lớn hơn 0
        if (quantity > 0) {
            selectedFood.push({
                foodId: foodId.innerText,  // Lấy foodId từ text trong ô foodId
                quantity: quantity         // Số lượng món ăn
            });
        }
    });

    return selectedFood;
}