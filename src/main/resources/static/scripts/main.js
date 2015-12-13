(function (document, window) {
  'use strict';

  let container = document.querySelector(".container");

  function insert(object) {
    let productElement = document.createElement("li");
    productElement.innerHTML = object.name;
    container.appendChild(productElement);
  }

  function getProducts() {
    window.fetch("/api/v1/products")
      .then((response) => {
        return response.json();
      })
      .then((products) => {
        products.forEach(insert);
      });
  }

  document.addEventListener("DOMContentLoaded", getProducts);
})(document, window);
