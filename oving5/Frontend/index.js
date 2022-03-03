function sendCode(){
    const codeInput = document.getElementById("codeTextArea").value;
    fetch("http://localhost:8080/compileAndRun", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(codeInput)
    })
    .then(res => {
        return res.json();
    })
    .then(resCode => console.log(resCode))
}