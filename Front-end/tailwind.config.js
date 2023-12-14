/** @type {import('tailwindcss').Config} */
import plugin from 'tailwindcss/plugin';

module.exports = {
    darkMode: 'class',
    mode: 'jit',
    content: [
        './src/**/*.{html,ts}',
        './node_modules/tw-elements/dist/js/**/*.js',
    ],
    theme: {
        extend: {
            fontFamily: {
                poppins: '"Poppins", sans-serif',
                'lilita-one': '"Lilita One", sans-serif',
                preahvihear: '"Preahvihear", sans-serif',
            },
            dropShadow: {
                'white-sm': ['0 2px 4px rgb(255 255 255)'],
                'black-sm': ['0 2px 4px rgb(0 0 0)'],
                white: [
                    '0 0px 10px rgb(255 255 255)',
                    '0 0px 60px rgb(255 255 255)',
                ],
                black: ['0 0px 10px rgb(0 0 0)', '0 0px 60px rgb(0 0 0)'],
            },
        },
    },
    plugins: [
        require('tw-elements/dist/plugin.cjs'),
        plugin(function ({ addVariant }) {
            addVariant('child', '&>*');
            addVariant('child-hover', '&>*:hover');
            addVariant('child-focus', '&>*:focus');
            addVariant('child-active', '&>*:active');
            addVariant('child-valid', '&>*:valid');
            addVariant('child-invalid', '&>*:invalid');
            addVariant('children', '& *');
            addVariant('children-hover', '& *:hover');
        }),
    ],
};
