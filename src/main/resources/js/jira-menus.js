AJS.toInit(async function () {
    await setPropInDialog();
})

async function setPropInDialog() {
    document.querySelectorAll('a.user-hover').forEach(userMention => {
        const currentUser = document.querySelector("meta[name='ajs-remote-user']").content;
            userMention.addEventListener('mouseover', evt => {
                (async () => {
                    console.log(">>> inside :: addEventListener");
                    const resp = await fetch(`/rest/api/2/user/properties/department?username=${currentUser}`)
                    const res = await resp.json();

                    const userPropKey = res.key;
                    const userPropValue = res.value.department;
                    let newProp = document.createElement('div');
                    newProp.innerHTML = `<div>
                            <p>Department: ${userPropValue}</p>
                        </div>`;
                    console.log(userMention)
                    console.log(userMention.querySelector('.user-hover-details'))
                    userMention.querySelector('.user-hover-details').parentElement.appendChild(newProp);
                })()
            });
    });
}