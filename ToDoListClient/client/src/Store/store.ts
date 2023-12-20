import { create } from 'zustand';
import { Task } from '@types';
import { getTodosFromDb } from '@api/getTodosFromDb';
import { addTodoDb } from '@api/addTodoDb';
import { deleteTodoDb } from '@api/deleteTodoDb';
import { updateTodoDb } from '@api/updateTodoDb';
import {logout} from "@api/logout";



interface UserState {
	username?: string;
	loginUser: (username: string) => void;
	logoutUser: () => void;
}

export const useUser = create<UserState>((set)=>({
	loginUser: async (username:string) => {
		set( {
			username: username,
		});
	},

	logoutUser: async () => {
		await logout();

		set( {
			username: '',
		});
	},
}))

interface TodosState {
	todos: Task[];
	getTodosFromDbAndSet: () => void;
	addTodo: (title: string) => void;
	deleteTodo: (id: number) => void;
	editTodo: (task: Task) => void;
	doneTodo: (task: Task) => void;
}

export const useTodos = create<TodosState>((set) => ({
	todos: [],

	getTodosFromDbAndSet: async () => {
		const tasks = await getTodosFromDb();

		set({
			todos: tasks,
		});
	},

	addTodo: async (title) => {
		const idFromDb = await addTodoDb(title);

		set((state) => ({
			todos: [...state.todos, { id: idFromDb, title, done: false }],
		}));
	},

	deleteTodo: async (id) => {
		await deleteTodoDb(id);

		set((state) => ({
			todos: state.todos.filter((todo) => {
				return todo.id !== id;
			}),
		}));
	},

	editTodo: async (task: Task) => {
		await updateTodoDb(task);

		set((state) => ({
			todos: state.todos.map((todo) => {
				todo.id === task.id && (todo.title = task.title);
				return todo;
			}),
		}));
	},

	doneTodo: async (task: Task) => {
		await updateTodoDb(task);

		set((state) => ({
			todos: state.todos.map((todo) => {
				todo.id === task.id && (todo.done = !todo.done);
				return todo;
			}),
		}));
	},
}));
