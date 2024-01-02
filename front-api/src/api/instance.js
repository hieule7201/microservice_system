import axios from "axios";
const baseURL = 'http://localhost:8080/'

const instance = axios.create({
    baseURL: baseURL
})
export const IMG_URL = baseURL + 'book/images/'
export default instance