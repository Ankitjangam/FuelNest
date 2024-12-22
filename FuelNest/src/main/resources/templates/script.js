// Open/Close Sign-Up Modal
const signupBtn = document.getElementById("signupBtn");
const signupModal = document.getElementById("signupModal");
const closeModal = document.getElementById("closeModal");

signupBtn.addEventListener("click", () => {
    signupModal.style.display = "flex";
});

closeModal.addEventListener("click", () => {
    signupModal.style.display = "none";
});

// Open/Close Login Modal
const loginBtn = document.getElementById("loginBtn");
const loginModal = document.getElementById("loginModal");
const closeModalLogin = document.getElementById("closeModalLogin");

loginBtn.addEventListener("click", () => {
    loginModal.style.display = "flex";
});

closeModalLogin.addEventListener("click", () => {
    loginModal.style.display = "none";
});

// Close Modals When Clicking Outside the Content
window.addEventListener("click", (e) => {
    if (e.target === signupModal) signupModal.style.display = "none";
    if (e.target === loginModal) loginModal.style.display = "none";
});
