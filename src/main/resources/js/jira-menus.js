
AJS.init(function() {
    document.querySelectorAll('a.user-hover').forEach(userMention => {

        console.log(userMention)
        const currentUser = document.querySelector("meta[name='ajs-remote-user']").content;

        userMention.addEventListener('mouseover', evt => {
            fetch(`/rest/api/2/user/properties/department?username=${currentUser}`)
                .then(res => res.json())
                .then(res => {
                    const userPropKey = res.key;
                    const userPropValue = res.value.department;
                    let newProp = document.createElement('div');
                    newProp.innerHTML = `<div>
                            <p>Department: ${userPropValue}</p>
                        </div>`;
                    console.log(">>>>>")
                    console.log(newProp)
                    userMention.querySelector('#user-hover-info').appendChild(newProp);
                });
        });
    });
})