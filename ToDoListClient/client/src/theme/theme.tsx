'use client';
import { createTheme, responsiveFontSizes } from '@mui/material';

declare module '@mui/material/styles' {
	interface Palette {
		backgroundColors: Palette['primary'];
		border: Palette['primary'];
	}

	interface PaletteOptions {
		backgroundColors?: PaletteOptions['primary'];
		border?: PaletteOptions['primary'];
	}
}

let theme = createTheme({
	palette: {
		primary: {
			main: '#79818c',
			dark: '#000000',
		},
		secondary: {
			main: '#2775fc',
			dark: '#6d18f5',
		},
		border: {
			main: '#dce1e6',
		},
		backgroundColors: {
			main: '#ffffff',
			dark: '#edeef0',
		},
	},
	typography: {
		fontFamily: ['sans-serif', 'Verdana', 'Roboto'].join(','),
	},
});

theme = responsiveFontSizes(theme);

export default theme;
