// 注意 Vite 要求变量名有特定前缀。这确保了你的开发设置与生产或其他环境隔离，提高了代码的安全性和可维护性。
export const serverIp = import.meta.env.VITE_API_SERVER_IP
// export const serverIp = '120.79.148.126'
// console.log(serverIp)