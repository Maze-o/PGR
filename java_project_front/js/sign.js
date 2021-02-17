    function chkSign() {
        const signupFrm = document.getElementById('signupFrm')
        const email = signupFrm.email
        const emailVal = signupFrm.email.value
        const emailChk = /^[\w.\-_]+@$/i

        if (!emailChk.test(emailVal)) {
            alert('잘못된 이메일 형식 입니다')
            email.focus()
        }
    }

    function changeDomain() {
        const domainSelect = document.getElementById('domainSelect')
        const textEmail = document.getElementById('textEmail')

        if (domainSelect.value == 'direct') {
            textEmail.disabled = false
            textEmail.value = null
            textEmail.focus()
        } else {
            textEmail.disabled = true
            textEmail.value = domainSelect.value
        }
    }