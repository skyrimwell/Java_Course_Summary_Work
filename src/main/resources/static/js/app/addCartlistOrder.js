let countSet = () => {
    let countByUrl = new URLSearchParams(location.search).getAll("count[]");
    countByUrl.forEach((item, index) => {
        countSetForUrl(item, index)
    });
}

let countSetForUrl = (count,index) => {
    let countInHtml = document.getElementsByClassName("count");
    countInHtml[index].innerHTML = count;
    let count1InHtml = document.getElementsByClassName("count1");
    count1InHtml[index].innerHTML = count;
}

let totalPriceSet = () => {
    let totalPrice = document.getElementsByClassName("totalPrice");
    let table = document.getElementById("table");
    let tbody = table.children[1];
    Array.from(tbody.children).forEach((item, index) => {
        let price = item.children[2].innerHTML*1;
        let count = item.children[3].innerHTML*1;
        let sum = price * count;
        totalPrice[index].innerHTML = sum;
    })
}

let totalAmountSet = () => {
    let totalAmount = document.getElementById("totalAmount");
    let sum = 0;
    let table = document.getElementById("table");
    let tbody = table.children[1];
    Array.from(tbody.children).forEach((item) => {
        let price = item.children[4].innerHTML*1;
        console.log(price);
        sum = sum + price;
    })
    totalAmount.innerHTML = sum;
}

let init = () => {
    countSet();
    totalPriceSet();
    totalAmountSet();
}

init()