let factsseen=1;

isPlaying = false;

function loadCatfacts(catfact){
    if(!isPlaying) {
        var audio = new Audio('RobotRock.mp3');
    audio.play();
    audio.volume = 0.3;
    audio.currentTime = 23.5;
    isPlaying = true;
    }
    document.getElementById("name").innerHTML = catfact.text.substring(0,catfact.text.length-1).toUpperCase() + generateCounterSuffix();
    document.getElementById("totalfactsseen").innerHTML = "Kat Fakt Kounter: " + factsseen + generateCounterSuffix();
    document.getElementById("dadbod").style.backgroundColor =  "#" + Math.floor(Math.random()*16777215).toString(16);
    document.getElementById("name").style.backgroundColor =  "#" + Math.floor(Math.random()*16777215).toString(16);
    document.getElementById("totalfactsseen").style.backgroundColor =  "#" + Math.floor(Math.random()*16777215).toString(16);
    document.getElementById("faxtitle").style.color =  "#" + Math.floor(Math.random()*16777215).toString(16);
    var button = document.getElementById("catfactsubmit");
    button.style.width = (Math.random() * 100 + 100) + "px";
    button.style.height = (Math.random() * 100 + 100) + "px";
    button.style.backgroundColor = "#" + Math.floor(Math.random()*16777215).toString(16);
    factsseen++;
}

function generateCounterSuffix() {
    suffix = "!"
    for(i = 0; i < 5; i++) {
        console.log(Math.random() * 100 + 100);
        if(Math.floor(Math.random()*10+1) % 2 == 1){
            suffix = suffix + "!";
        }
        else {
            suffix = suffix + "1";
        }
    }
    return suffix;
}

function getCatfact(){//AJAX call
    console.log("in get catfact");
    //Step 1: Create new XHR Object
    var xhr = new XMLHttpRequest();
    //Step 2: Define onreadystatechange function
    xhr.onreadystatechange = function(){
        console.log("in ORSC");
        if(xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText);
            var catfact = JSON.parse(xhr.responseText);
            loadCatfacts(catfact);
        }
    }
    //Step 3: Open our request
    xhr.open("GET","https://cat-fact.herokuapp.com/facts/random",true);
    //Step 4: Send our request
    xhr.send();//No args except when posting, we are getting
}



window.onload = function(){
    console.log("in inload");
    document.getElementById("catfactsubmit").addEventListener("click",getCatfact);
    
}