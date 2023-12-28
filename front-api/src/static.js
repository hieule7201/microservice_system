export const book = [
  {
    id: 1,
    name: "I see yellow flower on green glass",
    img: "https://moviescoremedia.com/newsite/wp-content/uploads/2020/05/yellowflowers-1.jpg",
    genres: "Loving",
    total: 12,
  },
  {
    id: 2,
    name: "One Piece",
    img: "https://images2.thanhnien.vn/528068263637045248/2023/7/5/anime-16885290131791004759743.jpg",
    genres: "Manga",
    total: 5,
  },
  {
    id: 3,
    name: "Hell Collage",
    img: "https://vtv1.mediacdn.vn/562122370168008704/2023/9/27/edit-photo-2-1695778890293205655283.png",
    genres: "Horror",
    total: 2,
  },
  {
    id: 4,
    name: "Hell Collage",
    img: "https://vtv1.mediacdn.vn/562122370168008704/2023/9/27/edit-photo-2-1695778890293205655283.png",
    genres: "Horror",
    total: 2,
  },
];
export const borrow = [
  {
    id: 1,
    book: {
      id: 3,
      name: "Hell Collage",
      img: "https://vtv1.mediacdn.vn/562122370168008704/2023/9/27/edit-photo-2-1695778890293205655283.png",
      genres: "Horror",
      total: 2,
    },
    status: "BORROWING",
    dateStart: "20/12/2023 15:14:11",
    dateEnd: null,
  },
  {
    id: 2,
    book: {
      id: 3,
      name: "Hell Collage",
      img: "https://vtv1.mediacdn.vn/562122370168008704/2023/9/27/edit-photo-2-1695778890293205655283.png",
      genres: "Horror",
      total: 2,
    },
    status: "BORROWING",
    dateStart: "20/12/2023 15:14:11",
    dateEnd: null,
  },
];
export const links = [
  {
    path: '/',
    name: 'home'
  },
  {
    path: '/book',
    name: 'book'
  },
  {
    path: '/borrow',
    name: 'borrow'
  }
]
