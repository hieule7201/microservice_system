import instance from '/instance.js'

const borrowBook = async (borrow) =>{
    return await instance.post('/borrow/borrow_book', borrow)
}
const getBorrow = async (keyword, statusBorrow) => {
    return await  instance.get('/borrow/get_borrow', {
        params: {
            keyword: keyword,
            statusBorrow: statusBorrow
        }
    })
}
const getDetailBorrow = async (idBorrow) => {
    return await instance.get(`/borrow/get_borrow_detail/${idBorrow}`)
}
const returnBorrow = async (idBorrow) => {
    return await instance.put(`/borrow/give_back/${idBorrow}`);
}

export {borrowBook, getDetailBorrow, getBorrow, returnBorrow}