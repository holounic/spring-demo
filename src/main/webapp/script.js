const JSON_HEADER = new Headers({'Content-Type': 'application/json'});

const displayElement = (targetId, element) => document.getElementById(targetId).innerHTML = element;

function jsonToCat(json) {
  const nameStr = `<h3>Name: ${json['name']}</h3>`;
  const ageStr = `<p>Age: ${json['age']}</p>`;
  const colour = json['colour'];
  const colourStr = `<p style="color: rgb(${colour['red']}, ${colour['green']}, ${colour['blue']})">colour</p>`;
  const priceStr = `<p>Price: ${json['price']}</p>`;
  return nameStr + ageStr + colourStr + priceStr;
}

const getId = () => Number.parseInt(document.getElementById('cat-id').elements[0].value);

function showCat() {
  fetch(`/show/${getId()}`)
      .then(response => response.json())
      .then(json => displayElement('cat-window', jsonToCat(json)));
}


const paymentRequestBody = (size, isCash) => `{"size": ${size}, "isCash": ${isCash}}`;
const jsonToChange = (json) => `<p>Change: ${json['size']}</p>`;

function buyCat() {
  const paymentInfo = document.getElementById('payment-input').elements;
  const size = Number.parseInt(paymentInfo[0].value);
  const method = paymentInfo[1].value;
  const requestBody = paymentRequestBody(size, method === "cash" ? 1 : 0);
  const request = new Request(`/buy/${getId()}`, {method: 'POST', body: requestBody, headers: JSON_HEADER});

  fetch(request)
      .then(response => response.json())
      .then(json => {
        console.log(json);
        displayElement('cat-window', jsonToCat(json['cat']));
        displayElement('change-window', jsonToChange(json['change']));
      });
}