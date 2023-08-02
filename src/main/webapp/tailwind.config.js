/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./webapp/**/*.{html,js,jsp}"],
  theme: {
    extend: {},
  },
  plugins: [require("daisyui")],
  daisyui: {
    themes: ["light", "dark", "cupcake"],
  },
}

