import api from "./Api"

export const login = async (password, username) => {
    try {
        const response = await api.post("/api/user/login",{
            password: password,
            username: username
        });
        return response.data;
    } catch (error) {
        console.log(error.response);
    }
}