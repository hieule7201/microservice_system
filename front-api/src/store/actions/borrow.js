import {borrowBook, getBorrow, returnBorrow} from "@/api/borrow.js";


const getAllBorrowAction = async ({commit}, keyword, statusBorrow) => {
    try {
        const data = await getBorrow(keyword, statusBorrow)
        commit('setAllBorrow', data.data)
    }catch (e){
        console.log(e.data.response.message)
    }
}
const borrowBookAction = async ({commit}, borrow) => {
    try {
        const data = await borrowBook(borrow)
        commit('setMessageAPI', data.data)
    }catch (e){
        console.log(e.data.response.message)
    }
}
const returnBorrowAction = async ({commit}, idBorrow) => {
    try {
        const data = await returnBorrow(idBorrow)
        commit('setMessageAPI', data.data)
    }catch (e){
        console.log(e.data.response.message)
    }
}

export {returnBorrowAction, borrowBookAction, getAllBorrowAction}