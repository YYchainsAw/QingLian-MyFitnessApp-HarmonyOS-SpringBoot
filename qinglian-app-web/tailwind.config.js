/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  darkMode: "class",
  theme: {
    extend: {
      colors: {
        "tertiary-container": "#00e5ff",
        "surface-tint": "#005bc1",
        "inverse-on-surface": "#f0f1f2",
        "on-secondary-fixed-variant": "#454749",
        "on-primary-fixed-variant": "#004493",
        "secondary": "#5d5e61",
        "tertiary-fixed-dim": "#00daf3",
        "on-surface-variant": "#3b494c",
        "on-secondary-fixed": "#1a1c1e",
        "primary-fixed-dim": "#adc6ff",
        "on-tertiary-fixed-variant": "#004f58",
        "error-container": "#ffdad6",
        "outline": "#6b7a7d",
        "surface": "#f8f9fa",
        "surface-bright": "#f8f9fa",
        "surface-container-low": "#f3f4f5",
        "error": "#ba1a1a",
        "on-primary-container": "#0055b6",
        "on-primary-fixed": "#001a41",
        "surface-container-highest": "#e1e3e4",
        "background": "#f8f9fa",
        "tertiary-fixed": "#9cf0ff",
        "on-surface": "#191c1d",
        "outline-variant": "#bac9cc",
        "on-primary": "#ffffff",
        "on-tertiary": "#ffffff",
        "surface-variant": "#e1e3e4",
        "on-secondary-container": "#636467",
        "on-background": "#191c1d",
        "inverse-primary": "#adc6ff",
        "inverse-surface": "#2e3132",
        "on-tertiary-container": "#00626e",
        "secondary-fixed": "#e2e2e5",
        "primary": "#005bc1",
        "surface-dim": "#d9dadb",
        "on-secondary": "#ffffff",
        "surface-container-lowest": "#ffffff",
        "tertiary": "#006875",
        "surface-container": "#edeeef",
        "primary-container": "#bcd0ff",
        "on-tertiary-fixed": "#001f24",
        "secondary-container": "#e2e2e5",
        "surface-container-high": "#e7e8e9",
        "on-error": "#ffffff",
        "secondary-fixed-dim": "#c6c6c9",
        "on-error-container": "#93000a",
        "primary-fixed": "#d8e2ff"
      },
      borderRadius: {
        "DEFAULT": "0.125rem",
        "lg": "0.25rem",
        "xl": "0.5rem",
        "full": "0.75rem"
      },
      fontFamily: {
        "headline": ["Manrope"],
        "body": ["Inter"],
        "label": ["Inter"]
      }
    },
  },
  plugins: [
    require('@tailwindcss/typography'),
    require('@tailwindcss/forms'),
    require('@tailwindcss/container-queries')
  ],
}
