import api from "./Api"

export const login = async (username, password) => {
    try {
        const response = await api.post("/api/user/login",{
            password: password,
            name: username
        });
        return response.data;
    } catch (error) {
        console.log(error.response);
    }
}

export const register = async (username, email, password) => {
    try {
        const response = await api.post("/api/user/register",{
            password: password,
            name: username,
            email: email
        });
        return response.data;
    } catch (error) {
        console.log(error.response);
    }
}