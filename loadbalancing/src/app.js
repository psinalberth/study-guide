import express from "express";
const app = express();

const SERVER_NAME = process.env.SERVER_NAME || "App";
const PORT = process.env.PORT || 3000;
const ALGORITHM = process.env.ALGORITHM || "round-robin";

const hello = (res) => {
  res.send({
    server: SERVER_NAME,
    message: `Hello from server ${SERVER_NAME}.`,
    algorithm: ALGORITHM,
    timestamp: new Date().toISOString(),
  });
};

app.get("", (req, res) => {
  const delay = req.query.delay;
  const server = req.query.server;
  console.log(
    `Request received at server ${SERVER_NAME} with delay=${delay} and server=${server}`
  );
  (delay && "#" + server === SERVER_NAME) || ALGORITHM === "least-conn"
    ? setTimeout(() => hello(res), delay)
    : hello(res);
});

app.listen(PORT, () => {
  console.log(`Server ${SERVER_NAME} is running on port ${PORT}`);
});
export default app;
