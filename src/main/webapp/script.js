const JSON_HEADER = new Headers({'Content-Type': 'application/json'});

const getElement = (id) => document.getElementById(id);
const displayElement = (targetId, element) => getElement(targetId).innerHTML = element;

const jsonToColour = (json) => `rgb(${json['red']}, ${json['green']}, ${json['blue']})`;
function jsonToCat(json) {
  const nameStr = `<h3>Name: ${json['name']}</h3>`;
  const ageStr = `<p>Age: ${json['age']}</p>`;
  const colour = json['colour'];
  const colourStr = `<p style="background-color: ${jsonToColour(colour)};">colour</p>`;
  const priceStr = `<p>Price: ${json['price']}</p>`;
  return nameStr + ageStr + colourStr + priceStr;
};
const paymentToJson = (size, method) => `{"size": ${size}, "method": \"${method}\"}`;
const jsonToPayment = (payment) => `<p>Earned: ${payment["size"]}</p>`;
const jsonToChange = (json) => `<p>Change: ${json['size']}</p>`;
const colourToJson = (r, g, b) => `{"r": ${r}, "g": ${g}, "b":${b}}`;
const catToJson = (name, age, price, colour) => `{"age": ${age}, "colour": ${colour}, "name": "${name}", "price": ${price}}`;

const getId = () => Number.parseInt(getElement('cat-id').elements[0].value);

function getBalance() {
  fetch('/balance')
      .then(response => response.json())
      .then(json => {
        displayElement('balance', json);
      });
}

function showCat() {
  fetch(`/show/${getId()}`)
      .then(response => response.json())
      .then(json => displayElement('cat-window', jsonToCat(json)));
}

function buyCat() {
  const paymentInfo = getElement('payment-input').elements;
  const size = Number.parseInt(paymentInfo[0].value);
  const method = paymentInfo[1].value;
  const requestBody = paymentToJson(size, method === "cash" ? "CASH" : "CARD");
  //log
  console.log(requestBody);
  const request = new Request(`/buy/${getId()}`, {method: 'POST', body: requestBody, headers: JSON_HEADER});

  fetch(request)
      .then(response => response.json())
      .then(json => {
        displayElement('cat-window', jsonToCat(json['cat']));
        displayElement('change-window', jsonToChange(json['change']))})
      .then(() => getBalance());
}

function sellCat() {
  const sellInfo = getElement('cat-for-sale').elements;
  const name = sellInfo['name'].value;
  const age = Number.parseInt(sellInfo['age'].value);
  const price = Number.parseInt(sellInfo['price'].value);
  const r = Number.parseInt(sellInfo['r'].value);
  const g = Number.parseInt(sellInfo['g'].value);
  const b = Number.parseInt(sellInfo['b'].value);
  const requestBody = catToJson(name, age, price, colourToJson(r, g, b));
  const request = new Request('/sell', {method: 'POST', body: requestBody, headers: JSON_HEADER});

  fetch(request)
      .then(response => response.json())
      .then(json => displayElement('payment-window', jsonToPayment(json)))
      .then(() => getBalance());
}

function buyCoffee() {
  const coffeeInfo = getElement('coffee-info').elements;
  const paymentSize = Number.parseInt(coffeeInfo['payment-size'].value);
  const type = coffeeInfo['type'].value;
  const requestBody = `{"size": ${paymentSize}, "method": "CASH", "type": "${type}"}`;
  fetch('/buy/coffee', {method: 'POST', body: requestBody, headers: JSON_HEADER})
      .then(response => response.json())
      .then(json => console.log(json))
      .then(() =>  getBalance());
}
