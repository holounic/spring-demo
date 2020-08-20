function sendRequest() {
  fetch('/test')
      .then(response => response.json())
      .then(data => console.log(data));
}

function square() {
  let json = '{"integer": 2}';
  let header = new Headers();
  header.append('Content-type', 'application/json')
  let request = new Request('/square', {method: 'POST', body: json, headers: header});
  fetch(request)
      .then(response => response.json())
      .then(data => console.log(data))
}