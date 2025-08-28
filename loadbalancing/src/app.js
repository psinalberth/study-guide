import express from "express";
const app = express();

const SERVER_NAME = process.env.SERVER_NAME || "App";
const PORT = process.env.PORT || 3000;
const ALGORITHM = process.env.ALGORITHM || "round-robin";

const algorithms = {
  'least-conn': (res, delay) => {
    setTimeout(() => greetings(res), delay);
  },
  default: (res, _) => {
    greetings(res)
  }
};

const greetings = (res) => {
  res.send({
    server: SERVER_NAME,
    message: `Hello from server ${SERVER_NAME}.`,
    algorithm: ALGORITHM,
    timestamp: new Date().toISOString(),
  });
};

app.get("", (req, res) => {
  const delay = req.query.delay;
  console.log(
    `Request received at server ${SERVER_NAME} with delay=${delay}`
  );
    const alg = algorithms[ALGORITHM] || algorithms.default;
    alg(res, delay);
});

app.get(/.*/, (req, res) => {
  const delay = req.query.delay;
  console.log(
    `Request received at server ${SERVER_NAME} with delay=${delay}`
  );
    const alg = algorithms[ALGORITHM] || algorithms.default;
    alg(res, delay);
});


app.listen(PORT, () => {
  console.log(`Server ${SERVER_NAME} is running on port ${PORT}`);
});

export default app;
