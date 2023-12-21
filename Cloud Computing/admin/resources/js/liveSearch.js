// Live Search
const liveSearchInput = document.getElementById("liveSearchInput");
const tables = document.querySelectorAll("table");

liveSearchInput.addEventListener("keyup", function (e) {
    const searchString = e.target.value.toLowerCase();

    tables.forEach((table) => {
        const tableRows = table.querySelectorAll("tbody tr");

        tableRows.forEach((row) => {
            const nama = row
                .querySelector("td:nth-child(1)")
                .textContent.toLowerCase();
            const username = row
                .querySelector("td:nth-child(2)")
                .textContent.toLowerCase();
            const email = row
                .querySelector("td:nth-child(3)")
                .textContent.toLowerCase();

            if (
                nama.includes(searchString) ||
                username.includes(searchString) ||
                email.includes(searchString)
            ) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        });
    });
});
