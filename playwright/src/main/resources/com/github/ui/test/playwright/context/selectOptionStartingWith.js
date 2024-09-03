// noinspection BadExpressionStatementJS
/**
 * Getter for the first option starting with the given prefix.
 * This function will wait up to 10 seconds, to check for changes in
 * the datalist.
 *
 * @param {HTMLInputElement} node the input element with the data list
 * @param {string} prefix the prefix to check
 * @returns {Promise<string>} the promise resolving to the full value of the matching option
 */
(node, prefix) => new Promise((resolve, reject) => {
    const timeout = 10; //seconds
    const dataList = node.list
    if (dataList === null) {
        reject(new Error('The node has no data list'));
        return;
    }

    let counter = timeout * 20;

    function checkOptions() {
        const options = Array();
        for (const child of dataList.options) {
            options.push(child.value);
        }

        counter--;
        if (counter === 0) {
            reject(new Error(`No option starting with '${prefix}' found in ${timeout} seconds. Available options are: ${options.join(', ')}`));
            return;
        }

        const optionStartingWith = options.find(value => value.startsWith(prefix));
        if (optionStartingWith !== undefined) {
            resolve(optionStartingWith);
        } else {
            setTimeout(checkOptions, 50);
        }
    }

    checkOptions();
})