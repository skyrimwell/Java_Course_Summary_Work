let totalPrice;
let backLocation = () => {
    let brickUid = new URLSearchParams(location.search).get("brickUid");
    window.location.href="/bricks/brickdetail?uid="+brickUid;
}