window.onload = init;

function init() {
  let myFile = document.querySelector("#myFile");
  let fileName = document.querySelector("#fileName");
  let preview = document.querySelector(".preview");

  //這裡是上傳圖片的監聽事件
  myFile.addEventListener("change", function () {
    if (preview.querySelectorAll("img")) {
      let picture = preview.querySelectorAll("img");
      let checkbox = preview.querySelectorAll("input");
      let div = preview.querySelectorAll("div");
      for (let i = 0; i < picture.length; i++) {
        picture[i].remove();
        checkbox[i].remove();
        div[i].remove();
      }
    }

    if (this.files) {
      for (let i = 0; i < this.files.length; i++) {
        let file = this.files[i];
        if (file.type.indexOf("image") > -1) {
          fileName.value = file.name;

          let reader = new FileReader();
          reader.readAsDataURL(file);
          reader.addEventListener("load", function (e) {
            // console.log(e.target.result);
            let div = document.createElement("div");
            let img = document.createElement("img");
            let checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            img.src = e.target.result;
            preview.append(div);
            div.classList.add("forDiv");
            div.append(checkbox);
            checkbox.classList.add("positionForCheckbox");
            div.append(img);
            img.style.width = "20em";
            img.style.height = "20em";
            img.style.backgroundSize = "cover";
          });
        } else {
          alert("Not a File");
          myFile.value = "";
          fileName.value = "";
        }
      }
    }
  });
  //刪除圖片的監聽事件

  let clear = document.querySelector("#clear");

  clear.addEventListener("click", function () {
    let checkbox = document.querySelectorAll(".positionForCheckbox");
    let picture = preview.querySelectorAll("img");
    let div = preview.querySelectorAll("div");
    for (let i = 0; i < checkbox.length; i++) {
      if (checkbox[i].checked) {
        picture[i].remove();
        div[i].remove();
        checkbox[i].remove();
      }
    }
  });
 
    $(".container").fadeIn(1000);
}
