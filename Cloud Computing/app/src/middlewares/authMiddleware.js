import jwt from 'jsonwebtoken';
import "dotenv/config";

const secret = process.env.JWT_SECRET;

const auth = (req, res, next) => {
    const authHeader = req.headers.authorization;
    
    if(authHeader){
        const token = authHeader.split(' ')[1];
        jwt.verify(token, secret, (err, user) => {
            if(err){
                return res.status(403).json({
                    status: "Error",
                    message: "Invalid or Expired Token..."
                })
            }
            req.user = user;
            next();
        })
    }
    else{
        return res.status(401).json({
            status: "Error",
            message: "Invalid or Expired Token..."
        })
    }
}

export default auth;